/**
 * Problem: Implement the compression algorithm.
 * Solution: Following their design.
 * Runtime: O(N*w*w) where N is the length of the inputString and w is the window width.
 * Space: O(1)
 */
String losslessDataCompression(String inputString, int width) {
    String ret = "";
    
    // loop through input string
    for(int i=0; i<inputString.length(); i++) {
        
        int windowStart = Math.max(0, i-width); // obtain window start. windows can be less than width
        int windowEnd = i;
        int windowLength = windowEnd - windowStart;
        int largestLength = Integer.MIN_VALUE; // record the largest length
        int smallestStartIndex = Integer.MAX_VALUE; // record the smallest start index
        boolean found = false;
        
        // System.out.println(i + " CURRENT LETTER: " + inputString.substring(i,i+1));
        // System.out.println("WINDOW: " + inputString.substring(windowStart, windowEnd));
        
        // loop through startIndex
        for(int startIndex = windowStart; startIndex < windowEnd; startIndex ++ ) { // iterating from windowStart to windowEnd
            // loop through length
            for(int length = 1; length <= Math.min(windowLength, inputString.length() - i); length ++ ) { // iterating from 1 to min of windowLength and end of input string
                // System.out.println(inputString.substring(i, i+length) + " - " + inputString.substring(startIndex, startIndex+length));

                // check to see if the current input string is equal to a section we have already covered
                if(inputString.substring(i, i+length).equals(inputString.substring(startIndex, startIndex+length))) {
                    
                    // System.out.println("FOUND: " + inputString.substring(i, i+length) + " " + inputString.substring(startIndex, startIndex+length) + " : " + startIndex + " " + length);
                    
                    found = true;

                    // if length is larger than largestLength, reset largestLength and smallestStartIndex
                    if(largestLength < length) {
                        largestLength = length;
                        smallestStartIndex = startIndex;
                    }
                    // if length is equal to largestLength, check if we can update smallestStartIndex
                    if(largestLength == length) {
                        if(smallestStartIndex > startIndex) {
                            smallestStartIndex = startIndex;
                        }
                    }
                
                }
            }
        }
        
        // append to return. if found, add new section, else, add character
        if(found) {
            ret += "(" + smallestStartIndex + "," + largestLength + ")";
            i += largestLength-1;
        }
        else {
            ret += inputString.substring(i,i+1);
        }
    }
    return ret;
}
