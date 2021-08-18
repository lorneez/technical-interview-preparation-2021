/**
 * Problem: Check for 10 consecutive incorrect passwords.
 * Solution: Iterate through attempts array and keep a count of consecutive incorrect passwords.
 * Runtime: O(N)
 * Space: O(1)
 */
boolean incorrectPasscodeAttempts(String passcode, String[] attempts) {
    int count = 0; // counter for consecitive incorrect passwords
    for(String attempt : attempts) {
        if(!attempt.equals(passcode)) {
            count ++;
            if(count == 10) return true;
        }
        else {
            count = 0;
        }
    }
    return false;
}
