package bot;

import java.util.Map;

public class MultiusersGame {
    public Long firstId;
    public Long secondId;
    public Long winnerId;

    public Map<Long, Integer> scores;
    public Map<Long, Boolean> status;

    public MultiusersGame(){
      scores.put(firstId, 0);
      scores.put(secondId, 0);
      status.put(firstId, false);
      status.put(secondId, false);
    }

    public void setWinnerId(){
      if (scores.get(firstId) - scores.get(secondId) > 0){
        winnerId = firstId;
      }
      else if (scores.get(firstId) - scores.get(secondId) < 0){
        winnerId = secondId;
      } else {
        return;
      }
    }

}
