package Mingle.MingleProject.service;

import Mingle.MingleProject.Mapper.EntityDTOMapper;
import Mingle.MingleProject.dto.CommentsDTO;
import Mingle.MingleProject.dto.PostDTO;
import Mingle.MingleProject.entity.CommentsEntity;
import Mingle.MingleProject.entity.GatheringEntity;
import Mingle.MingleProject.entity.PostEntity;
import Mingle.MingleProject.repository.CommentsRepository;
import Mingle.MingleProject.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final CommentsRepository commentsRepository;

    public List<PostDTO> findPost(String logInId) {
        List<PostEntity> postEntities = postRepository.findBypMId(logInId);
        List<PostDTO> postDTOList = new ArrayList<>();
        for (PostEntity postEntity: postEntities) {
            postDTOList.add(EntityDTOMapper.entityToDTO(postEntity));
            System.out.println(postDTOList);
        }

        return postDTOList;
    }

    public void uploadPost(PostDTO postDTO) {
        PostEntity postEntity = EntityDTOMapper.dtoToEntity(postDTO);
        System.out.println(postEntity.getPGNum());
        System.out.println(postEntity.getPBNum());
        System.out.println(postEntity.getPNum());

        postRepository.save(postEntity);
    }

    public void writeComments(CommentsDTO commentsDTO) {
        CommentsEntity commentsEntity = EntityDTOMapper.DTOToEntity(commentsDTO);

        commentsRepository.save(commentsEntity);
    }



    public List<Long> commentsCount(List<PostDTO> postDTOList) {
        List<Long> commentsCount = new ArrayList<>();
        for (PostDTO postDTO: postDTOList) {
            commentsCount.add(commentsRepository.findCPNumCount(postDTO.getPNum()));
        }
        System.out.println(commentsCount);
        System.out.println(commentsCount);
        System.out.println(commentsCount);
        System.out.println(commentsCount);
        System.out.println(commentsCount);


        return commentsCount;
    }
}
