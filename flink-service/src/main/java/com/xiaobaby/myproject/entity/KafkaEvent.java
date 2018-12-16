package com.xiaobaby.myproject.entity;

/**
 * @author Lu Yufeng
 * @date 2018/8/8 上午10:44
 */
public class KafkaEvent {

    private String keyWord;

    private int frequency;

    public KafkaEvent(){}

    public KafkaEvent(String keyWord, Integer frequency) {
        this.keyWord = keyWord;
        this.frequency = frequency;
    }


    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    @Override
    public String toString() {

        return keyWord + "," + frequency ;
    }

    public static KafkaEvent fromString(String eventStr) {
        String[] split = eventStr.split(",");
        return new KafkaEvent(split[0], Integer.valueOf(split[1]));
    }
}
