package pwr.project.ziwg.services;

import com.fuzzylite.Engine;
import com.fuzzylite.imex.FisImporter;
import com.fuzzylite.term.Activated;
import com.fuzzylite.term.Aggregated;
import com.fuzzylite.term.Term;
import com.fuzzylite.variable.OutputVariable;
import com.google.common.util.concurrent.AtomicDouble;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import pwr.project.ziwg.dto.DaysEmotionCategoryDto;
import pwr.project.ziwg.dto.DaysEmotionsCategoryDto;
import pwr.project.ziwg.enums.FisInput;
import pwr.project.ziwg.enums.FisOutput;
import pwr.project.ziwg.repository.UserRepository;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class FisService {

    private final UserRepository userRepository;

    private final FisImporter fisImporter;

    @Autowired
    public FisService(
            final UserRepository userRepository
    ) {
        fisImporter = new FisImporter();
        this.userRepository = userRepository;
    }


    private String calcEngine(Map<FisInput, Integer> inputs) throws IOException {

        File file = ResourceUtils.getFile("classpath:emotions.fis");

        Engine engine = fisImporter.fromFile(file);

        engine.getInputVariable(FisInput.RADOSC.getName()).setValue(inputs.get(FisInput.RADOSC));
        engine.getInputVariable(FisInput.SMUTEK.getName()).setValue(inputs.get(FisInput.SMUTEK));
        engine.getInputVariable(FisInput.DEPRESJA.getName()).setValue(inputs.get(FisInput.DEPRESJA));

        engine.process();
        OutputVariable output = engine.getOutputVariable(FisOutput.DIAGNOZA.getName());
        String kk = this.fuzzyOutputValue(output);
        return kk;
    }

    public String getEmotionsFis(String uid) throws IOException {
        Map<FisInput, Integer> inputs = new HashMap<>(Map.of(
                FisInput.RADOSC, 0,
                FisInput.DEPRESJA, 0,
                FisInput.SMUTEK, 0));

        this.userRepository.getEmotionsByUserUid(uid)
                .stream().filter(d -> !d.getEmotionsCategories().isEmpty()).sorted(this::compareDates)
                .limit(16)
                .map(DaysEmotionCategoryDto::of).map(DaysEmotionCategoryDto::getEmotionsCategories)
                .forEach(d -> inputs.compute(d, (key, old) -> old+1));

        return this.calcEngine(inputs);
    }



    private int compareDates(DaysEmotionsCategoryDto a, DaysEmotionsCategoryDto b) {
        return -1 * getDate(a.getDate()).compareTo(getDate(b.getDate()));
    }

    private LocalDate getDate(String date) {
       return LocalDate.parse(date);
    }

    private String fuzzyOutputValue(OutputVariable outputVariable) {
        AtomicDouble max = new AtomicDouble(0L);
        AtomicReference<String> termName = new AtomicReference<>("");

        Aggregated aggregated = outputVariable.fuzzyOutput();
        outputVariable.getTerms().forEach(d -> {
            double activation = aggregated.activationDegree(d);
            if(activation > 0.001 && activation - max.get() >= 0.001) {
                max.set(activation);
                termName.set(d.getName());
            }
        });

        return termName.get();
    }

}
