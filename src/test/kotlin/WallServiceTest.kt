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
        val audio = Audio(1, 2, "Track Title", "Artist", 180)
        val video = Video(2, 3, "Video Title", "Video Description", 360)
        val photo = Photo(3, 4, "Photo Title", "link")
        val document = Document(4, 5, "Document Title", 1024)
        val link = Link("https://ya.ru", "ya.ru")
        val initialPost = Post(
            10, 12, 54, 20, "record test test", 5,
            true, false, true, true, attachments = listOf(
                AudioAttachment(audio),
                VideoAttachment(video),
                PhotoAttachment(photo),
                DocumentAttachment(document),
                LinkAttachment(link)
            )
        )
        val addPost = WallService.add(initialPost)

        assertEquals(1, addPost.id)
    }

    @Test
    fun likedById() {
       // val posts = ArrayList<Post>() //определили переменную
       // val id = 1 // установили Ид переданного поста для лайка 1
       // val post = Post(1, 12, 54, 20, "record test test", 5, true, false, true, true)
        val audio = Audio(1, 2, "Track Title", "Artist", 180)
        val video = Video(2, 3, "Video Title", "Video Description", 360)
        val photo = Photo(3, 4, "Photo Title", "link")
        val document = Document(4, 5, "Document Title", 1024)
        val link = Link("https://ya.ru", "ya.ru")
        val post = Post(
            1, 12, 54, 20, "record test test", 5, true, false, true, true, attachments = listOf(
                AudioAttachment(audio),
                VideoAttachment(video),
                PhotoAttachment(photo),
                DocumentAttachment(document),
                LinkAttachment(link)
            )
        )
        WallService.add(post)

//        for ((index, post) in posts.withIndex()) {
//            if (post.id == id) {
//                posts[index] =
//                    post.copy(likes = post.likes + 1, answer = post.answer?.copy(), messege = post.messege?.copy())
//                assertEquals(6, posts[index].likes) //проверяем что количество лайков стало больше
//            }
//        }
        WallService.likedById(1)
        val updatedPost = WallService.getPosts().first { it.id == 1 }

        assertEquals(6, updatedPost.likes)

    }

    @Test
    fun update() {
        val audio = Audio(1, 2, "Track Title", "Artist", 180)
        val video = Video(2, 3, "Video Title", "Video Description", 360)
        val photo = Photo(3, 4, "Photo Title", "link")
        val document = Document(4, 5, "Document Title", 1024)
        val link = Link("https://ya.ru", "ya.ru")
        val initialPost = Post(
            1, 12, 54, 20, "record test test", 5, true, false, true, true, attachments = listOf(
                AudioAttachment(audio),
                VideoAttachment(video),
                PhotoAttachment(photo),
                DocumentAttachment(document),
                LinkAttachment(link)
            )
        )
        WallService.add(initialPost)

        val updatedPost = Post(
            1, 12, 54, 20, "NEW NEW record1 test test", 5, true, false, true, true, attachments = listOf(
                AudioAttachment(audio),
                VideoAttachment(video),
                PhotoAttachment(photo),
                DocumentAttachment(document),
                LinkAttachment(link)
            )
        )
        val result = WallService.update(updatedPost)

        assertTrue(result)
        val updatedPostInList = WallService.getPosts().first { it.id == 1 }
        assertEquals("NEW NEW record1 test test", updatedPostInList.text)
    }

    @Test
    fun NotUpdate() {
        val audio = Audio(1, 2, "Track Title", "Artist", 180)
        val video = Video(2, 3, "Video Title", "Video Description", 360)
        val photo = Photo(3, 4, "Photo Title", "link")
        val document = Document(4, 5, "Document Title", 1024)
        val link = Link("https://ya.ru", "ya.ru")
        val initialPost = Post(
            1, 12, 54, 20, "record test test", 5,
            true, false, true, true, attachments = listOf(
                AudioAttachment(audio),
                VideoAttachment(video),
                PhotoAttachment(photo),
                DocumentAttachment(document),
                LinkAttachment(link)
            )
        )
        val isUpdated = WallService.update(initialPost)

        assertEquals(isUpdated,false)
    }
    @Test
    fun getPosts() {
        val audio = Audio(1, 2, "Track Title", "Artist", 180)
        val video = Video(2, 3, "Video Title", "Video Description", 360)
        val photo = Photo(3, 4, "Photo Title", "link")
        val document = Document(4, 5, "Document Title", 1024)
        val link = Link("https://ya.ru", "ya.ru")
        val post1 = Post(
            1, 12, 54, 20, "record test test", 5, true, false, true, true, attachments = listOf(
                AudioAttachment(audio),
                VideoAttachment(video),
                PhotoAttachment(photo),
                DocumentAttachment(document),
                LinkAttachment(link)
            )
        )
        WallService.add(post1)

        val post2 = Post(
            2, 312, 254, 120, "record2222 test test", 15, true, false, true, true, attachments = listOf(
                AudioAttachment(audio),
                VideoAttachment(video),
                PhotoAttachment(photo),
                DocumentAttachment(document),
                LinkAttachment(link)
            )
        )
        WallService.add(post2)

        val resultPosts = WallService.getPosts()

        assertEquals(2, resultPosts.size)
    }
}