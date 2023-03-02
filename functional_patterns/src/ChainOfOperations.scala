object ChainOfOperations extends App {
  /*
  * A lot of times we need to perform several operations on a variable or data structure.  Often this requires
  * storing multiple intermediate results, which can create long and messy code.  With functional programming, it's
  * often possible to chain several operations together without having to store any temporary results.
  * */

  // Class representing a video
  case class Video(title: String, video_type: String, length: Int)

  /*
  * We would like to be able to take a collection of videos, find the ones that are cat videos, and sum up all of
  * their lengths (i.e. we want to calculate the total length of cat videos in the collection).  One way to do this
  * would be store intermediate results, but will Scala we can simply chain multiple operations together.  First we
  * call filter to find any cat videos.  We then call map and extract the lengths of the filtered videos.  Finally, we
  * call sum to add all of the extracted lengths together.  Note that we perform all of these operations in a single
  * line, without storing any temporary results.
  * */
  def timeOfCatVideos(videos: Seq[Video]): Int = {
    videos.filter(_.video_type == "cat").map(_.length).sum
  }

  // 3 videos and a list containing all 3
  val video1 = Video("Pianocat Plays Carnegie Hall", "cat", 300)
  val video2 = Video("Paint Drying", "home-improvement", 600)
  val video3 = Video("Fuzzy Kittens Live at the Apollo", "cat", 250)
  val videos = List(video1, video2, video3)

  // Find the total length of cat videos
  println(s"Total length of all the cat videos: ${timeOfCatVideos(videos)}")  // Should be 550 (300 + 250)
}