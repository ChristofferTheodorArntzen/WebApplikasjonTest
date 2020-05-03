package codeCaseFremtind;

import codeCaseFremtind.letterService.LetterEntity;
import codeCaseFremtind.fagsystem.insurance.Insurance;
import codeCaseFremtind.fagsystem.user.User;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Database {

    private static Database database = null;

    private Map<Integer, User> userMap = new HashMap<>();
    private Map<Integer, Insurance> insuranceMap = new HashMap<>();
    private Map<Integer, LetterEntity> letterMap = new HashMap<>();

    public static Database getDatabase(){

        if(database == null){
            database = new Database();
        }
        return database;
    }

// User
/*--------------------------------------------------------------------------------------------------------------------*/

    public boolean insertUser(User user){
        userMap.put(user.getId(), user);
        return true;
    }

    public void deleteUser(User user){
        userMap.remove(user.getId());
    }

    public void updateOrCreateUser(User user){
        userMap.put(user.getId(), user);
    }

    public User findUser(int id){
        return userMap.get(id);
    }

    public int nextUserId(){

        if(userMap.size() == 0){
            return 1;
        }

        User user = userMap.values().stream().max(Comparator.comparing(User::getId)).get();
        return user.getId() + 1;
    }


    // Insurance
/*--------------------------------------------------------------------------------------------------------------------*/
    public boolean insertInsurance(Insurance insurance){
        insuranceMap.put(insurance.getId(), insurance);
        return true;
    }

    public void deleteInsurance(Insurance insurance){
        insuranceMap.remove(insurance.getId());
    }

    public void updateOrCreateInsurance(Insurance insurance){
        insuranceMap.put(insurance.getId(), insurance);
    }

    public Insurance findInsurance(int id){
        return insuranceMap.get(id);
    }

    public int nextInsuranceId(){

        if(insuranceMap.size() == 0){
            return 0;
        }

        Insurance insurance = insuranceMap.values().stream().max(Comparator.comparing(Insurance::getId)).get();
        return insurance.getId() + 1;
    }
}