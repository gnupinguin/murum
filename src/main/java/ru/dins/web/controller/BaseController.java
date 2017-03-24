package ru.dins.web.controller;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.dins.web.persistance.ActionRepository;
import ru.dins.web.persistance.LikeRepository;
import ru.dins.web.persistance.PostRepository;

import java.util.Calendar;

/**
 * Created by gnupinguin on 24.03.17.
 */
@Component @Data
public class BaseController {
    protected Calendar calendar = Calendar.getInstance();

    @Autowired
    protected PostRepository postRepository;

    @Autowired
    protected LikeRepository likeRepository;

    @Autowired
    protected ActionRepository actionRepository;
}
