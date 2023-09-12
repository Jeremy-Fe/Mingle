package Mingle.MingleProject.Mapper;

import Mingle.MingleProject.dto.*;
import Mingle.MingleProject.entity.*;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;

public class EntityDTOMapper {
    public static final ModelMapper modelMapper = new ModelMapper();

    public EntityDTOMapper () {
        modelMapper();
    }



    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    // 메소드 오버로딩을 이용하여 같은 이름의 메소드이지만 파라미터 값이 다르기에 작동 가능
    // 혹시 몰라서 그냥 다 맵핑 만들어씀

    /* 사용 방법
    Entity에서 DTO로
    PostEntity postEntity = // Get or create a PostEntity instance
    PostDTO postDTO = PostMapper.entityToDto(postEntity);

    DTO에서 Entity로
    PostDTO postDTO = // Get or create a PostDTO instance
    PostEntity postEntity = PostMapper.dtoToEntity(postDTO);
    */

    // 게시판 맵핑
    public static BoardDTO entityToDTO(BoardEntity boardEntity) {
        return modelMapper.map(boardEntity, BoardDTO.class);
    }
    public static BoardEntity DTOToEntity(BoardDTO boardDTO) {
        return modelMapper.map(boardDTO, BoardEntity.class);
    }

    // 게시글 맵핑
    public static PostDTO entityToDTO(PostEntity postEntity) {
        return modelMapper.map(postEntity, PostDTO.class);
    }
    public static PostEntity DTOToEntity(PostDTO postDTO) {
        return modelMapper.map(postDTO, PostEntity.class);

    }

    // 게시글 사진 맵핑


    // 댓글 맵핑
    public static CommentsDTO entityToDTO(CommentsEntity commentsEntity) {
        return modelMapper.map(commentsEntity, CommentsDTO.class);
    }
    public static CommentsEntity DTOToEntity(CommentsDTO commentsDTO) {
        return modelMapper.map(commentsDTO, CommentsEntity.class);
    }

    // 답글 맵핑
    public static ReplyDTO entityToDTO(ReplyEntity replyEntity) {
        return modelMapper.map(replyEntity, ReplyDTO.class);
    }
    public static ReplyEntity DTOToEntity(ReplyDTO replyDTO) {
        return modelMapper.map(replyDTO, ReplyEntity.class);
    }

    // 일정 맵핑
    public static ScheduleDTO entityToDTO(ScheduleEntity scheduleEntity) {return modelMapper.map(scheduleEntity, ScheduleDTO.class);}
    public static ScheduleEntity DTOToEntity(ScheduleDTO scheduleDTO) {return modelMapper.map(scheduleDTO, ScheduleEntity.class);}
}
