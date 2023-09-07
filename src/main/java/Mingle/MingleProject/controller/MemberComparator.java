package Mingle.MingleProject.controller;

import Mingle.MingleProject.dto.GatheringDTO;
import Mingle.MingleProject.dto.MemberDTO;
import Mingle.MingleProject.entity.Gathering;

import java.util.Comparator;

public class MemberComparator implements Comparator<MemberDTO> {
    private final GatheringDTO gatheringDTO;

    public MemberComparator(GatheringDTO gatheringDTO) {
        this.gatheringDTO = gatheringDTO;
    }

    @Override
    public int compare(MemberDTO member1, MemberDTO member2) {
        int position1 = getPositionInHierarchy(member1);
        int position2 = getPositionInHierarchy(member2);

        if (position1 < position2) {
            return -1;
        } else if (position1 > position2) {
            return 1;
        } else {
            return member1.getMName().compareTo(member2.getMName());
        }
    }

    private int getPositionInHierarchy(MemberDTO member) {
        if (member.getMId().equals(gatheringDTO.getGMainleader())) {
            return 1;
        } else if (member.getMId().equals(gatheringDTO.getGSubleader1()) ||
                member.getMId().equals(gatheringDTO.getGSubleader2()) ||
                member.getMId().equals(gatheringDTO.getGSubleader3())) {
            return 2;
        } else {
            return 3;
        }
    }
}
