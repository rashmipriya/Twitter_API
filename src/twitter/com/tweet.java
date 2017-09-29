package twitter.com;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.json.simple.JSONObject;

import twitter4j.HashtagEntity;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;


public class tweet  extends TimerTask{
 
	/* (non-Javadoc)
	 * @see java.util.TimerTask#run()
	 */
	@SuppressWarnings("unchecked")
	public void run(){
    	
	ConfigurationBuilder configurationbuilder = new ConfigurationBuilder();
	
	configurationbuilder.setDebugEnabled(true);
	configurationbuilder.setOAuthConsumerKey("**********************");
	configurationbuilder.setOAuthConsumerSecret("*********************");
	configurationbuilder.setOAuthAccessToken("****************************");
	configurationbuilder.setOAuthAccessTokenSecret("********************************");
	
	TwitterFactory tf = new TwitterFactory(configurationbuilder.build());
	twitter4j.Twitter twitter = tf.getInstance();
	JSONObject obj = new JSONObject();
	
	ArrayList<Status> tweets = new ArrayList<Status>();
	Query query = new Query("#Trump");
	QueryResult result = null;
	try {
		result = twitter.search(query);
		 tweets.addAll(result.getTweets());
		
	} catch (TwitterException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	for (Status st : tweets) {
		
		obj.put("screen_name",st.getUser().getScreenName());
		obj.put("user_details", st.getUser());
		obj.put("location", st.getGeoLocation());
		obj.put("followers", st.getUser().getFollowersCount());
		obj.put("new_id", st.getId());
		obj.put("text", st.getText());
		obj.put("createdAt", st.getCreatedAt());
		obj.put("source", st.getSource());
		obj.put("hashtag", st.getHashtagEntities());
		System.out.print(obj);    
	}
 }
    
    public static void main(String args[]){
    	Timer timer = new Timer();
    	timer.schedule(new tweet(), 0, 960000);
    	    
    }
}
