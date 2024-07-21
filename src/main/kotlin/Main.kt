data class Answer(val id: Int, val date: Int)
data class Messege(val id: Int, val date: Int)

abstract class Attachment(val type: String)

data class Audio(val id: Int, val owner_id: Int, val title: String, val artist: String, val duration: Int)
data class AudioAttachment(val audio: Audio) : Attachment("audio")

data class Video(val id: Int, val owner_id: Int, val title: String, val description: String, val duration: Int)
data class VideoAttachment(val video: Video) : Attachment("video")

data class Photo(val id: Int, val owner_id: Int, val title: String, val link: String)
data class PhotoAttachment(val photo: Photo) : Attachment("photo")

data class Document(val id: Int, val owner_id: Int, val title: String, val size: Int)
data class DocumentAttachment(val document: Document) : Attachment("document")

data class Link(val url: String, val title: String)
data class LinkAttachment(val link: Link) : Attachment("link")

data class Post(
    val id: Int,
    val owner_id: Int,
    val from_id: Int?, // поле может принмать значение null
    val date: Int,
    val text: String?,// поле может принмать значение null
    val likes: Int,
    val can_pin: Boolean,
    val can_delete: Boolean,
    val can_edit: Boolean,
    val is_pinned: Boolean,
    val messege: Messege? = null,
    val answer: Answer? = null
)


object WallService {

    private var posts = ArrayList<Post>()
    private var nextId = 1

    fun clear() {
        posts = ArrayList<Post>()
        nextId = 1 // Сброс счетчика для id постов
    }
    fun add(post: Post): Post {
        val newPost = post.copy(id = nextId++, answer = post.answer?.copy(), messege = post.messege?.copy())
        posts += newPost
        return posts.last()
    }

    fun likedById(id: Int) {
        for ((index, post) in posts.withIndex()) {
            if (post.id == id) {
                posts[index] =
                    post.copy(likes = post.likes + 1, answer = post.answer?.copy(), messege = post.messege?.copy())
                println(posts[index].likes)
            }
        }
    }

    fun update(newPost: Post): Boolean {
        val index = posts.indexOfFirst { it.id == newPost.id }
        return if (index != -1) {
            posts[index] = newPost.copy()
            true
        } else {
            false
        }
    }

    fun getPosts(): ArrayList<Post> {
        return posts
    }

}

fun main() {
    //создали НезмПермеренную post и передали в data Post данные для поста
    //который уже создал запись данных
    val post = Post(
        1, 12, 54, 20, "record test test", 5,
        true, false, true, true
    )
    WallService.add(post)

    val post2 = Post(
        2, 312, 254, 120, "record2222 test test", 15,
        true, false, true, true
    )
    WallService.add(post2)

    //создали НеизПеременную liked и скопировали в нее "запись данных post" созданную ранее
    //только изменив поле likes добавил +3
    // val liked = post.copy(likes = post.likes + 3)
    WallService.likedById(1) //добавили 1 лайк

    WallService.update(post) //обновили пост 2

// Получение списка постов
    val posts = WallService.getPosts()
    println("Wall posts: $posts")


    // Обновляем пост с id = 1
    val updatedPost = Post(
        1, 12, 54, 20, "NEW NEW record1 test test", 5,
        true, false, true, true
    )
    val isUpdated = WallService.update(updatedPost)


    val postsNew = WallService.getPosts()
    println("Wall posts: $postsNew")
}