package bot;

public class MultiuserGame {
    public void gameStat(Game userFirst, Game userSecond) {
        String winnerSpeech = "Вы победили! ";
        String loserSpeech = "Вы проиграли! ";
        String resultByTime = "По времени, при равном счете";

        final int firstRightCount = userFirst.getRightCount();
        final int secondRightCount = userSecond.getRightCount();
        final long firstTime = userFirst.getTime();
        final long secondTime = userSecond.getTime();

        if (firstRightCount > secondRightCount) {
            String scores ="Счёт:" + firstRightCount + " - " + secondRightCount;
            result(userFirst, winnerSpeech + scores);
            result(userSecond, loserSpeech + scores);

        }
        else if (firstRightCount < secondRightCount) {
            String scores ="Счёт:" + secondRightCount + " - " + firstRightCount;
            result(userSecond, winnerSpeech + scores );
            result(userFirst, loserSpeech + scores);
        }
        else {
            if (firstTime < secondTime) {
                result(userFirst,winnerSpeech + resultByTime);
                result(userSecond,loserSpeech + resultByTime);
            }
            else if (firstTime > secondTime) {
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

