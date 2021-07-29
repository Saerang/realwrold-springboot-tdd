package io.realworld.favorite.api;

import io.realworld.article.api.dto.SingleArticleResponseDto;
import io.realworld.article.app.ArticleFavoriteMapper;
import io.realworld.user.app.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class FavoriteController {

    private final ArticleFavoriteMapper articleFavoriteMapper;
    private final AuthenticationService authenticationService;

    @PostMapping("/articles/{slug}/favorite")
    public SingleArticleResponseDto articleFavorite(@PathVariable String slug) {
        return articleFavoriteMapper.favoriteArticle(slug, authenticationService.getCurrentUserId());
    }

    @DeleteMapping("/articles/{slug}/favorite")
    public SingleArticleResponseDto articleUnfavorite(@PathVariable String slug) {
        return articleFavoriteMapper.unfavoriteArticle(slug, authenticationService.getCurrentUserId());
    }
}
