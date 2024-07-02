data class Post(
    val id: Int,
    val owner_id: Int,
    val from_id: Int,
    val date: Int,
    val text: String,
    val likes: Int,
    val can_pin: Boolean,
    val can_delete: Boolean,
    val can_edit: Boolean,
    val is_pinned: Boolean
)


object WallService {

    //private var posts = emptyArray<Post>()
    private val posts = ArrayList<Post>()
    private var nextId = 1

    fun add(post: Post): Post {
        val newPost = post.copy(id = nextId++)
        posts.add(newPost)
        return newPost
        posts += post
        return posts.last()
    }

    fun likedById(id: Int) {
        for ((index, post) in posts.withIndex()) {
            if (post.id == id) {
                posts[index] = post.copy(likes = post.likes + 1)
            }
        }
    }

    fun update(post: Post): Boolean {
        val index = posts.indexOfFirst { it.id == post.id }
        return if (index != -1) {
            posts[index] = post
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
    val liked = post.copy(likes = post.likes + 3)
    println(liked)


// Получение списка постов
    val posts = WallService.getPosts()
    println("Wall posts: $posts")


    // Обновляем пост с id = 1
    val updatedPost = Post(1, 12, 54, 20, "NEW NEW record1 test test", 5,
        true, false, true, true)
    val isUpdated = WallService.update(updatedPost)


    val postsNew = WallService.getPosts()
    println("Wall posts: $postsNew")
}