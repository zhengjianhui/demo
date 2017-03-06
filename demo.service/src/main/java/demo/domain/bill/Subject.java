package demo.domain.bill;


import demo.enums.SubjectType;

public interface Subject {
    
    Long getBlockId();
    
    SubjectType getSubjectType();

    Long getId();

    String getName();

    Long getRelationHouseId();
}
