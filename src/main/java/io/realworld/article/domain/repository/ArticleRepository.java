package io.realworld.article.domain.repository;

import io.realworld.article.domain.Article;
import io.realworld.favorite.app.enumerate.FavoriteType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    Optional<Article> findByUserId(long userId);

    Optional<Article> findBySlug(String slug);

    @Query(value = "select a from Article a " +
            "join fetch a.articleTags at " +
            "join fetch at.tag t " +
            "where t.tag = :tag",
    countQuery = "select count(a) from Article a join a.articleTags at join at.tag t where t.tag = :tag")
    Page<Article> findAllWithTagByTag(Pageable pageable, @Param("tag") String tag);

    List<Article> findByIdIn(List<Long> ids);
}
