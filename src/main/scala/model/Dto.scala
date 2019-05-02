package model

import spray.httpx.SprayJsonSupport
import spray.json.{DefaultJsonProtocol, RootJsonFormat}


case class Post(memberID: String, status: String, postID: String, post_date: String, updated_date: String, title: Option[String],
                description: Option[String], message: Option[String], post_type: Option[String],
                author: Option[String], author_avatar: Option[String], author_position: Option[String],
                author_current_employer: Option[String], thumbnail_url: Option[List[String]],
                provider_name: Option[String], provider_url: Option[String],
                post_url: Option[String], html: Option[String],
                readers: Option[List[String]], likes: Option[List[LikeDetails]], Shares: Option[List[String]])

case class Feed(_id: String, memberID: String, activityType: String, postDetails: Post, actorID: Option[String], actorName: Option[String], actorAvatar: Option[String], commentID: Option[String])


case class LikeDetails(likeID: String, like: Option[String], like_date: String)


object JsonRepo extends DefaultJsonProtocol with SprayJsonSupport {

  implicit val likeDetailsFormats: RootJsonFormat[LikeDetails] = jsonFormat3(LikeDetails)

  implicit val PostUpdateRequestFormats: RootJsonFormat[Post] = jsonFormat21(Post)
  implicit val FeedRequestFormats: RootJsonFormat[Feed] = jsonFormat8(Feed)

}