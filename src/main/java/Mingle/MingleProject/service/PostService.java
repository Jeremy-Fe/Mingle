package Mingle.MingleProject.service;

import Mingle.MingleProject.Mapper.EntityDTOMapper;
import Mingle.MingleProject.dto.CommentsDTO;
import Mingle.MingleProject.dto.PostDTO;
import Mingle.MingleProject.entity.CommentsEntity;
import Mingle.MingleProject.entity.GatheringEntity;
import Mingle.MingleProject.entity.PostEntity;
import Mingle.MingleProject.repository.CommentsRepository;
import Mingle.MingleProject.repository.MemberRepository;
import Mingle.MingleProject.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.DoubleStream;


@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final CommentsRepository commentsRepository;
    private final MemberRepository memberRepository;

    public List<PostDTO> findPosts(String logInId) {
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


        return commentsCount;
    }

    public PostDTO findPost(Long pNum) {
        Optional<PostEntity> optionalPostEntity = postRepository.findById(pNum);
        PostEntity postEntity = optionalPostEntity.get();

        PostDTO postDTO = EntityDTOMapper.entityToDTO(postEntity);
        return postDTO;
    }

    @Transactional
    public void updatePost(PostDTO postDTO) {
        // postDTO 게터로 가져와서 쿼리문 가기
        Long pNum = postDTO.getPNum();
        Long pBNum = postDTO.getPBNum();
        String pTitle = postDTO.getPTitle();
        String pContents = postDTO.getPContents();
        postRepository.updatePost(pNum, pBNum, pTitle, pContents);
    }

    @Transactional
    public void deletePost(Long pNum) {
        commentsRepository.deletePostComment(pNum);
        postRepository.deletePost(pNum);
    }

    @Transactional
    public void deleteComment(Long cNum) {
        commentsRepository.deleteComment(cNum);
    }
}
