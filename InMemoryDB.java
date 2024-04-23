import java.util.HashMap;

public class InMemoryDB {
    private HashMap<String, Integer> mainDB = new HashMap<>();
    private HashMap<String, Integer> transactionDB = null;

    public Integer get(String key) {
        if (isTransactionActive()) {
            return transactionDB.getOrDefault(key, mainDB.get(key));
        }
        return mainDB.get(key);
    }

    public void put(String key, int value) {
        if (!isTransactionActive()) {
            throw new IllegalStateException("No transaction in progress.");
        }
        transactionDB.put(key, value);
    }

    public void begin_transaction() {
        if (transactionDB != null) {
            throw new IllegalStateException("Transaction already.");
        }
        transactionDB = new HashMap<>();
    }

    public void commit() {
        if (!isTransactionActive()) {
            throw new IllegalStateException("No transaction in progress.");
        }
        mainDB.putAll(transactionDB);
        transactionDB = null;
    }

    public void rollback() {
        if (!isTransactionActive()) {
            throw new IllegalStateException("No transaction in progress.");
        }
        transactionDB = null;
    }

    private boolean isTransactionActive() {
        return transactionDB != null;
    }

    public static void main(String[] args) {
        InMemoryDB db = new InMemoryDB();
    }
}


        
        

        
        
             
        
            
        

        
        
        
        
         
        

        
        
        

        
        
             
        
            
        

        
        
        
        
        
        

        
        
        
        
        
        
        
    