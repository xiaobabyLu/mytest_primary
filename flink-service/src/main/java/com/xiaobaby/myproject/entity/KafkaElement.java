package com.xiaobaby.myproject.entity;

/**
 * @author Lu Yufeng
 * @date 2018/8/8 上午10:44
 */
public class KafkaElement {

    private String keyWord;

    private int frequency;

    private long timestamp;

    public KafkaElement(){}

    public KafkaElement(String keyWord, Integer frequency, Long timestamp) {
        this.keyWord = keyWord;
        this.frequency = frequency;
        this.timestamp = timestamp;
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

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
//        return "KafkaElement{" +
//                "keyWord='" + keyWord + '\'' +
//                ", frequency=" + frequency +
//                ", timestamp=" + timestamp +
//                '}';
        return keyWord + "," + frequency + "," + timestamp;
    }

    public static KafkaElement fromString(String eventStr) {
        String[] split = eventStr.split(",");
        return new KafkaElement(split[0], Integer.valueOf(split[1]), Long.valueOf(split[2]));
    }
}
