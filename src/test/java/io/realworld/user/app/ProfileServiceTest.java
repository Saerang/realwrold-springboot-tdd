package io.realworld.user.app;

import io.realworld.user.domain.FollowRelation;
import io.realworld.user.domain.User;
import io.realworld.user.domain.repository.FollowRelationRepository;
import io.realworld.user.domain.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class ProfileServiceTest {

    @Autowired
    ProfileService profileService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    FollowRelationRepository followRelationRepository;
    @Autowired
    EntityManager em;

    @Test 
    void getProfile() {
        //given
        User user = new User("user@email.com", "1234", "usrname");
        userRepository.save(user);
         
        //when
        User result = profileService.getProfile(user.getUsername());
         
        //then
        assertThat(result.getUsername()).isEqualTo(user.getUsername());
    }
    
    @Test
    void userFollow() {
        //given
        User follower = new User("followerd@email.com", "1234", "follower");
        User followee = new User("followee@email.com", "1234", "followee");
        userRepository.saveAll(Arrays.asList(follower, followee));

        // em.flush();
        // em.clear();

        //when
        User result = profileService.followUser(follower.getId(), followee.getUsername());

        //then
        assertThat(result.getFollowing()).isTrue();
        assertThat(result.getUsername()).isEqualTo(followee.getUsername());
    }

    @Test
    void userUnfollow() {
        //given
        User follower = new User("followerd@email.com", "1234", "follower");
        User followee = new User("followee@email.com", "1234", "followee");
        userRepository.saveAll(Arrays.asList(follower, followee));
        FollowRelation followRelation = new FollowRelation(follower.getId(), followee.getId());
        followRelationRepository.save(followRelation);

        em.flush();
        em.clear();

        //when
        User result = profileService.unfollowUser(follower.getId(), followee.getUsername());
        List<FollowRelation> followRelations = followRelationRepository.findByFollowRelationId_FollowerId(follower.getId());

        //then
        assertThat(result.getFollowing()).isFalse();
        assertThat(result.getUsername()).isEqualTo(followee.getUsername());
        assertThat(followRelations).hasSize(0);
    }
}
