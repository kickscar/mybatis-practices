package mybatis.mapper.ex06;

import domain.Guestbook;

public interface GuestbookMapper {
    int insert(Guestbook guestbook);
    int delete(Long no);
}
