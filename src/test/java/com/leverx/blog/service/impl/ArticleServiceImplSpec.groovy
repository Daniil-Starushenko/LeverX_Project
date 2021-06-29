package com.leverx.blog.service.impl

import com.leverx.blog.exception.entity.EntityNotFoundException
import com.leverx.blog.model.entity.Article
import com.leverx.blog.repository.mysql.ArticleRepository
import com.leverx.blog.repository.mysql.TagRepository;
import spock.lang.Specification
import spock.lang.Subject


class ArticleServiceImplSpec extends Specification {

    def articleRepository = Mock(ArticleRepository)
    def tagRepository = Mock(TagRepository)

    @Subject
    def articleService = new ArticleServiceImpl(articleRepository, tagRepository)

    def "get article by given id"() {
        given:
            def articleId = 1
            def article = new Article(id: articleId)

        when:
            def actualArticle = articleService.getArticle(articleId)

        then:
            1 * articleRepository.findArticleById(articleId) >> Optional.of(article)
            0 * _

            article.is(actualArticle)
    }

    def "throw entity not found exception if there is no article present with given id"() {
        given:
            def articleId = 1

        when:
            articleService.getArticle(articleId)

        then:
            1 * articleRepository.findArticleById(articleId) >> Optional.empty()
            0 * _

            def exc = thrown  EntityNotFoundException
            exc.message == "article with id: ${articleId} is not found"
    }

}