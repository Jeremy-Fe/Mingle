package Mingle.MingleProject.service;

import Mingle.MingleProject.Mapper.EntityDTOMapper;
import Mingle.MingleProject.dto.CommentsDTO;
import Mingle.MingleProject.entity.CommentsEntity;
import Mingle.MingleProject.repository.CommentsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentsService {
    private final CommentsRepository commentsRepository;
    public List<CommentsDTO> findComments(String logInId) {
        List<CommentsEntity> commentsEntityList = commentsRepository.findbycMId(logInId);
        List<CommentsDTO> CommentDTOList = new ArrayList<>();
        for (CommentsEntity commentsEntity : commentsEntityList) {
            CommentDTOList.add(EntityDTOMapper.entityToDTO(commentsEntity));
        }
        return CommentDTOList;
    }
}
