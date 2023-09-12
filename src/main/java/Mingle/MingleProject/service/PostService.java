package Mingle.MingleProject.service;

import Mingle.MingleProject.Mapper.EntityDTOMapper;
import Mingle.MingleProject.dto.PostDTO;
import Mingle.MingleProject.entity.GatheringEntity;
import Mingle.MingleProject.entity.PostEntity;
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
        PostEntity postEntity = EntityDTOMapper.DTOToEntity(postDTO);
        System.out.println(postEntity.getGatheringEntity());
        System.out.println(postEntity.getPNum());

        postRepository.save(postEntity);
    }
}
