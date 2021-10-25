package mybatis.mapper.ex07;

import domain.Guestbook;

import java.util.List;

public interface GuestbookMapper {
    int insert(Guestbook guestbook);
    List<Guestbook> findAll();
    Guestbook findByNo(Long no);
}
