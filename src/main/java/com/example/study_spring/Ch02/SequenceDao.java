package com.example.study_spring.Ch02;

public interface SequenceDao {
    public Sequence getSequence(String sequenceId);

    public int getNextValue(String sequenceId);
}
