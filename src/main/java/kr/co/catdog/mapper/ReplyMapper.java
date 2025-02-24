package kr.co.catdog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.catdog.domain.ReplyVO;

@Mapper
public interface ReplyMapper {
	
	int registerReply(ReplyVO replyVO);
	
	List<ReplyVO> getReply(ReplyVO replyVO);
	
	int replyCount(ReplyVO replyVO);
	
	int replyDelete(ReplyVO replyVO);
	
	List<ReplyVO> myReply(ReplyVO replyVO);
}
