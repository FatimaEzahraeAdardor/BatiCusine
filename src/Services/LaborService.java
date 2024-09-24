package Services;

import Entities.Labor;
import Entities.Project;
import Repository.Implementation.LaborRepository;

import java.util.List;
import java.util.Optional;

public class LaborService {
    LaborRepository laborRepository = new LaborRepository();
    public Labor save(Labor labor){
        return laborRepository.save(labor);
    }
    public Optional<Labor> findById(int id){
        return laborRepository.findById(id);
    }
    public List<Labor> findAll(){
        return laborRepository.findAll();
    }
    public Labor update(Labor labor){
        return laborRepository.update(labor);
    }
    public Boolean delete(int id){
        return laborRepository.delete(id);
    }
    public List<Labor> findByProject(Project project) {
        return laborRepository.findByProject(project);
    }
    public double calculateTotalLaborCost(Project project) {
        List<Labor> labors = findByProject(project);
        return labors.stream()
                .mapToDouble(Labor::calculateTotalCost)
                .sum();
    }

}
