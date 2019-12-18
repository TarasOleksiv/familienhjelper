package ua.petros.service;

import ua.petros.model.Member;

import java.util.List;
import java.util.UUID;


public interface MemberService {

    Member getById(UUID id);

    List<Member> getAll();

    void save(Member member);

    void delete(Member member);

    Member findByName(String name);

}
