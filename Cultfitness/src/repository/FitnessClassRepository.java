package repository;

import model.FitnessClass;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class FitnessClassRepository {
    private final Map<String, FitnessClass> classes = new LinkedHashMap<>();

    public void save(FitnessClass fitnessClass) {
        classes.put(fitnessClass.getId(), fitnessClass);
    }

    public FitnessClass findById(String classId) {
        return classes.get(classId);
    }

    public Collection<FitnessClass> findAll() {
        return classes.values();
    }
}

