package bot;

public class MultiuserGame {
    public void gameStat(Game userFirst, Game userSecond) {
        String winnerSpeech = "Вы победили!";
        String loserSpeech = "Вы проиграли!";
        String resultByTime = " По времени, при равном счете";

        final int firstRightCount = userFirst.getRightCount();
        final int secondRightCount = userSecond.getRightCount();
        final long firstTime = userFirst.getTime();
        final long secondTime = userSecond.getTime();

        if (firstRightCount > secondRightCount) {
            result(userFirst, winnerSpeech);
            result(userSecond, loserSpeech);
        }
        else if (firstRightCount < secondRightCount) {
            result(userSecond, winnerSpeech);
            result(userFirst, loserSpeech);
        }
        else {
            if (firstTime > secondTime) {
                result(userFirst,winnerSpeech + resultByTime);
                result(userSecond,loserSpeech + resultByTime);
            }
            else if (firstTime < secondTime) {
                result(userSecond,winnerSpeech + resultByTime);
                result(userFirst,loserSpeech + resultByTime);
            }
            else {
                result(userFirst,"Произошла ничья");
                result(userSecond,"Произошла ничья");
            }

        }
    }
    private void result(Game user, String speech) {
        user.setResultOfBattle(speech);
    }


}

