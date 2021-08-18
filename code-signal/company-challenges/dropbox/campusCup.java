/**
 * Problem: Order the schools based on total additional space, then by alphabetical order.
 * Solution: Create a map of email counts, convert it into additional space, sort the map entries
 * using a lambda function.
 * Runtime: O(N*log(N))
 * Space: O(N)
 */
String[] campusCup(String[] emails) {
    Map<String, Integer> counts = new HashMap<String, Integer>();
    for(String s : emails) { // O(N)
        String[] components = s.split("@");
        if(counts.containsKey(components[1])) { // ideally containsKey() runs in O(1)
            counts.put(components[1], counts.get(components[1]) + 20);
        }
        else {
            counts.put(components[1], 20);
        }
    }
    
    for(String key : counts.keySet()) { // O(N) or less
        int score = counts.get(key);
        int gb = 0;
        if(score >= 100) {
            gb = 3;
        }
        if(score >= 200) {
            gb = 8;
        }
        if(score >= 300) {
            gb = 15;
        }
        if(score >= 500) {
            gb = 25;
        }
        counts.put(key, gb);
    }
    
    // Create list of map entries
    List<Map.Entry<String, Integer> > list = new LinkedList<Map.Entry<String, Integer> >(counts.entrySet());
 
    Collections.sort(list, (i1, i2) -> { // O(N*log(N))
        if(i1.getValue() > i2.getValue()) { // if first element value is greater, return -1
            return -1;
        }
        if(i1.getValue() < i2.getValue()) { // if first element value is less, return 1
            return 1;
        }
        
        return i1.getKey().compareTo(i2.getKey()); // otherwise, return string comparison of keys
        // if first element key is less than the second element key, return negative
        // otherwise, return positive
        // if equal, return 0
    });
    
    // convert list into array
    String[] ret = new String[list.size()];
    int index = 0;
    for(Map.Entry<String, Integer> entry : list) {
        ret[index] = entry.getKey();
        index++;
    }
    
    return ret;
}
