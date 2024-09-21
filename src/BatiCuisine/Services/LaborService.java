package BatiCuisine.Services;

import BatiCuisine.Entities.Labor;
import BatiCuisine.Repository.Implementation.LaborRepository;

public class LaborService {
    LaborRepository laborRepository = new LaborRepository();
    public Labor save(Labor labor){
        return laborRepository.save(labor);
    }
}
