package codeCaseFremtind;

import codeCaseFremtind.letterService.LetterEntity;
import codeCaseFremtind.subjectSystem.Agreement;
import codeCaseFremtind.subjectSystem.User;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Database {

    private static Database database = null;

    private Map<Integer, User> userMap = new HashMap<>();
    private Map<Integer, Agreement> agreementMap = new HashMap<>();
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


    // AGREEMENT
/*--------------------------------------------------------------------------------------------------------------------*/
    public boolean insertAgreement(Agreement agreement){
        agreementMap.put(agreement.getId(), agreement);
        return true;
    }

    public void deleteAgreement(Agreement agreement){
        agreementMap.remove(agreement.getId());
    }

    public void updateOrCreateAgreement(Agreement agreement){
        agreementMap.put(agreement.getId(), agreement);
    }

    public Agreement findAgreement(int id){
        return agreementMap.get(id);
    }

    public int nextAgreementId(){

        if(agreementMap.size() == 0){
            return 0;
        }

        Agreement agreement = agreementMap.values().stream().max(Comparator.comparing(Agreement::getId)).get();
        return agreement.getId() + 1;
    }

    // AGREEMENT
/*--------------------------------------------------------------------------------------------------------------------*/
    public boolean insertLetter(LetterEntity letter){
        letterMap.put(letter.getId(), letter);
        return true;
    }

    public void updateOrCreateLetter(LetterEntity letter){
        letterMap.put(letter.getId(), letter);
    }

    public LetterEntity findLetter(int id){
        return letterMap.get(id);
    }

    public int nextLetterId(){

        if(letterMap.size() == 0){
            return 0;
        }

        LetterEntity letter = letterMap.values().stream().max(Comparator.comparing(LetterEntity::getId)).get();
        return letter.getId() + 1;
    }

}