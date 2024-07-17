import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class WallServiceTest {
    @Before
    fun clearBeforeTest() = WallService.clear()
    @Test
    fun add() {
//        val posts = ArrayList<Post>()
//        var nextId = 1
//        val post = Post(
//            1, 12, 54, 20, "record test test", 5, true, false, true, true
//        )
//        WallService.add(post)
//        val newPost = post.copy(id = nextId++, answer = post.answer?.copy(), messege = post.messege?.copy())
//        posts += newPost
//        assertEquals(2, nextId)
        val initialPost = Post(
            1, 12, 54, 20, "record test test", 5,
            true, false, true, true
        )
        val addPost = WallService.add(initialPost)

        assertEquals(2, addPost.id)
    }

    @Test
    fun likedById() {
        val posts = ArrayList<Post>() //определили переменную
        val id = 1 // установили Ид переданного поста для лайка 1
        val post = Post(
            1, 12, 54, 20, "record test test", 5, true, false, true, true
        )

        for ((index, post) in posts.withIndex()) {
            if (post.id == id) {
                posts[index] =
                    post.copy(likes = post.likes + 1, answer = post.answer?.copy(), messege = post.messege?.copy())
                assertEquals(6, posts[index].likes) //проверяем что количество лайков стало больше
            }
        }

    }

    @Test
    fun update() {

        val initialPost = Post(1, 12, 54, 20, "record test test", 5, true, false, true, true)
        WallService.add(initialPost)

        val updatedPost = Post(1, 12, 54, 20, "NEW NEW record1 test test", 5, true, false, true, true)

        val result = WallService.update(updatedPost)

        assertTrue(result)
        val updatedPostInList = WallService.getPosts().first { it.id == 1 }
        assertEquals("NEW NEW record1 test test", updatedPostInList.text)
    }

    @Test
    fun NotUpdate() {

        val initialPost = Post(
            1, 12, 54, 20, "record test test", 5,
            true, false, true, true
        )
        val isUpdated = WallService.update(initialPost)

        assertEquals(isUpdated,false)
    }
    @Test
    fun getPosts() {
        val post1 = Post(1, 12, 54, 20, "record test test", 5, true, false, true, true)
        WallService.add(post1)

        val post2 = Post(2, 312, 254, 120, "record2222 test test", 15, true, false, true, true)
        WallService.add(post2)

        val resultPosts = WallService.getPosts()

        assertEquals(2, resultPosts.size)
    }
}