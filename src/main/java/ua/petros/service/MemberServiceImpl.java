package ua.petros.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.petros.dao.MemberDao;
import ua.petros.model.Member;

import java.util.List;
import java.util.UUID;

/**
 * Created by Taras on 18-12-2019.
 */

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberDao memberDao;

    @Override
    public Member getById(UUID id) {
        return memberDao.getOne(id);
    }

    @Override
    public List<Member> getAll() {
        return memberDao.findAll();
    }

    @Override
    public void save(Member member) {
        memberDao.save(member);
    }

    @Override
    public void delete(Member member) {
        memberDao.delete(member);
    }

    @Override
    public Member findByName(String name) {
        return memberDao.findByName(name);
    }
}
