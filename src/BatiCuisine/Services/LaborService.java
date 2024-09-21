package BatiCuisine.Services;

import BatiCuisine.Entities.Labor;
import BatiCuisine.Repository.Implementation.LaborRepository;

import java.util.Optional;

public class LaborService {
    LaborRepository laborRepository = new LaborRepository();
    public Labor save(Labor labor){
        return laborRepository.save(labor);
    }
    public Optional<Labor> findById(int id){
        return laborRepository.findById(id);
    }
}
