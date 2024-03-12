package com.webnovel.cover.domain.service;

import com.webnovel.cover.domain.Cover;
import com.webnovel.cover.domain.CoverDto;
import com.webnovel.cover.domain.repository.CoverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CoverService {
    private final CoverRepository coverRepository;
    @Autowired
    public CoverService(CoverRepository coverRepository) {
        this.coverRepository = coverRepository;
    }

    @Transactional
    public void saveCover(CoverDto coverDto){
        coverRepository.save(coverDto.toEntity());
    }

    public Cover findByNovelId(Long novelId){
        return coverRepository.findByNovelId(novelId);
    }


}
