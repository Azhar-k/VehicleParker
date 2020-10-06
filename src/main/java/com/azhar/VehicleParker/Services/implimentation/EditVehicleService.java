package com.azhar.VehicleParker.Services.implimentation;

import com.azhar.VehicleParker.Dao.VehicleDao;
import com.azhar.VehicleParker.Entities.ApiResponses.EditVehicleResponse;
import com.azhar.VehicleParker.Entities.Vehicle.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EditVehicleService implements com.azhar.VehicleParker.Services.EditVehicleService {

    @Autowired
    VehicleDao vehicleDao;

    @Override
    public EditVehicleResponse insertVehicle(Vehicle inputVehicle) {
        EditVehicleResponse editVehicleResponse;
        Vehicle validVehicle = validateVehicle(inputVehicle);
        if(validVehicle==null){
            try {
                Vehicle vehicle=vehicleDao.insert(inputVehicle);
                editVehicleResponse=new EditVehicleResponse(true,"vehicle added",vehicle);

            } catch (Exception e) {
                editVehicleResponse=new EditVehicleResponse(true,e.getMessage(),null);
            }

        }
        else {
            editVehicleResponse=new EditVehicleResponse(true,"vehicle already exist",null);
        }
        return editVehicleResponse;
    }

    @Override
    public EditVehicleResponse deleteVehicle(Vehicle inputVehicle) {
        EditVehicleResponse editVehicleResponse;
        Vehicle validVehicle = validateVehicle(inputVehicle);
        if(validVehicle!=null){
            try {
                vehicleDao.delete(inputVehicle);
                editVehicleResponse=new EditVehicleResponse(true,"vehicle deleted",null);

            } catch (Exception e) {
                editVehicleResponse=new EditVehicleResponse(true,"something went wrong "+e.getMessage(),null);
            }

        }
        else {
            editVehicleResponse=new EditVehicleResponse(true,"vehicle do not exist",null);
        }
        return editVehicleResponse;

    }

    @Override
    public EditVehicleResponse editVehicle(Vehicle inputVehicle) {
        EditVehicleResponse editVehicleResponse;
        Vehicle validVehicle = validateVehicle(inputVehicle);
        if(validVehicle!=null){
            try {
                Vehicle vehicle=vehicleDao.update(inputVehicle);
                editVehicleResponse=new EditVehicleResponse(true,"vehicle edited",vehicle);

            } catch (Exception e) {
                editVehicleResponse=new EditVehicleResponse(true,"something went wrong "+e.getMessage(),null);
            }

        }
        else {
            editVehicleResponse=new EditVehicleResponse(true,"vehicle do not exist",null);
        }
        return editVehicleResponse;
    }

    @Override
    public Vehicle validateVehicle(Vehicle inputVehicle) {
        Vehicle vehicle=vehicleDao.getVehicleByName(inputVehicle.getName());

        return vehicle;
    }
}
