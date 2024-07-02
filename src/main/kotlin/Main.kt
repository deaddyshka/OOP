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
//) {
//    var likes = likes
//        set(value) {
//            if (value >= 0) {
//                field = value
//            }
//        }
//}

object Wallservice {

    private var posts = emptyArray<Post>()
    fun add(post: Post): Post {
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

}

fun main() {
    val post = Post(
        1, 12, 54, 20, "record test test", 5,
        true, false, true, true
    )

    //post.likes=7
    //println(post.id)
    //println(post.likes)

    val liked = post.copy(likes = post.likes + 3)
    println(liked)

}