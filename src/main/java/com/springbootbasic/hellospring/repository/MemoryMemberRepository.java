package com.springbootbasic.hellospring.repository;

import com.springbootbasic.hellospring.domain.Member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<Long, Member>();
    private static long sequence = 0;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {

        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {

        return new ArrayList<>(store.values());
    }
}
