/**
 * A program to carry on conversations with a human user.
 * This is the initial version that:
 * 
 * Sameer Saxena and Schuyler Kresge
 * 
 * Tasks
 * 1) handle response to good or bad moods
 * 2) handle request for time or date
 * 3) handle request for time until an event (spring break, easter, summer)
 * 4) history of EA bot
 * 5) US History
 * 6) Misc
 * 
 * My chatbot will respond appropriately to the user based on their mood after identifying the presence of mood based keywords.
 * 
 * Positive keywords: yes, love, awesome, best, great, amazing, good, nice, haha, wonderful, compelling, like, won, well
 *  I chose these as they were the most common words found in a database of positive tweets from my PJAS sentiment analysis project
 *  There were also some words which I had brainstormed after thinking of sentences showing positive emotion
 * Negative keywords: no, not, sad, headache, bad, dull, boring, stupid, worst, awful, mean, ugh, sigh, hate, lost, badly
 *  Like my positive keywords, these were the most common words found in a database of negative tweets from my PJAS project
 *  There were also some that I had brainstormed after thinking with others about sentences showing negative emotion
 * Some of the keywords chosen were specific to the questions being asked, too. For example, if Magpie asked "Do you enjoy what you do?" then the user would have to respond "yes" (positive) or "no" (negative)
 * As a future improvement, we may implement a thesaurus API to find if the words in the sentence given match any of our keywords
 * 
 */ 
public class Magpie
{
    /**
     * Get a default greeting
     * @return a greeting
     */
    
    String[] questions = {"How was school/work today?", "How are your friends?", "How is your family?", "How has your week been?", "Has it been a good year for you?", "Do you enjoy what you do?"}; 
    
    public String getGreeting()
    {
        return "How are you feeling today?";
    }
    
    public String getQuestion() 
    {
        int myRand = (int)(Math.random() * questions.length);
        String question = questions[myRand];
        questions[myRand] = "Anything else you want to talk about?";
        return question;
    }

    /**
     * Gives a response to a user statement
     *
     * @param statement
     *            the user statement
     * @return a response based on the rules given
     */
    public String getResponse(String statement)
    {
        String response = "";
        boolean positive = false;
        boolean negative = false;
        boolean neutral = true;
        String[] posResponses = {"I’m so happy to hear that!", "That's awesome!", "Wow, I’m so proud of you!", "Haha, me too!", "Hey, that's great for you!"};
        String[] negResponses = {"That sucks!", "Huh, you are a rude person.", "That's not good", "I feel so bad for you", "Hopefully things get better"};
        String[] positives = {"yes", "love", "awesome", "best", "great", "amazing", "good", "nice", "haha", "wonderful", "compelling", "like", "won", "well"};
        String[] negatives = {"no", "not", "sad", "headache", "bad", "dull", "boring", "stupid", "worst", "awful", "mean", "ugh", "sigh", "hate", "lost"};
        for (int p=0; p < positives.length; p++) {
            if (findKeyword(statement, positives[p], 0) > -1) {
                positive = true; 
                neutral = false;
                break;
            }
        }
        for (int n=0; n < negatives.length; n++) {
            if (findKeyword(statement, negatives[n], 0) > -1) {
                negative = true;
                neutral = false;
                break;
            }
        }
        if (positive && !negative) {
            int myRand = (int)(Math.random() * posResponses.length);
            response = posResponses[myRand];
        }
        else if (negative || (positive && negative)) {
            int myRand = (int)(Math.random() * negResponses.length);
            response = negResponses[myRand];
        }
        else 
            response = getRandomResponse();
    
        //response = getRandomResponse();
        
        return response;
    }

    /**
     * Pick a default response to use if nothing else fits.
     * @return a non-committal string
     */
    private String getRandomResponse()
    {
        String response = "I don't know how to respond to that.";
        return response;
    }
    
    private int findKeyword(String statement, String goal, int startPos){
        int spot = -1;
        statement = statement.toLowerCase();
        statement = " " + statement + " ";
        statement = statement.replaceAll("[-+.^:,!?']()", "");
        spot = statement.indexOf(" " + goal + " ");
        if (spot < 0) {
            spot = statement.indexOf(" " + goal + "s ");
        }
        return spot;
    }
    
}