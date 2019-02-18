package com.binarydistrict.scala.exercise6.sql

import com.binarydistrict.scala.exercise6.sql.SQL
import com.binarydistrict.scala.exercise6.sql.SQL.VideoInfo
import org.scalatest.{BeforeAndAfterAll, FlatSpec, Matchers}
import scalikejdbc.{ConnectionPool, DB}

class SQLSpec extends FlatSpec with Matchers with BeforeAndAfterAll {
  "Solution" should "count videos in each category" in {
    SQL.VideoInfoSQL.countVideosInEachCategory() should contain theSameElementsAs Map(null.asInstanceOf[String] -> 1312, "Gaming" -> 869, "News & Politics" -> 4680,
      "Sports" -> 1744, "Comedy" -> 2345, "Entertainment" -> 4770, "Music" -> 1391, "Pets & Animals" -> 562, "Howto & Style" -> 1839,
      "Shows" -> 167, "Travel & Events" -> 235, "Education" -> 649, "People & Blogs" -> 8886, "Autos & Vehicles" -> 1349,
      "Movies" -> 1, "Science & Technology" -> 911, "Film & Animation" -> 2572)
  }

  it should "count sum of likes in each category" in {
    SQL.VideoInfoSQL.countLikesEachCategory() should contain theSameElementsAs Map(null.asInstanceOf[String] -> 11752083, "Gaming" -> 9506367, "News & Politics" -> 11345517,
      "Sports" -> 9467341, "Comedy" -> 42323588, "Entertainment" -> 65556063, "Music" -> 76215414, "Pets & Animals" -> 2157531,
      "Howto & Style" -> 11052286, "Shows" -> 453877, "Travel & Events" -> 787157, "Education" -> 1843748, "People & Blogs" -> 48500742,
      "Autos & Vehicles" -> 14891846, "Movies" -> 1732, "Science & Technology" -> 17385597, "Film & Animation" -> 12954804)
  }

  it should "find the most viewed copy of each video" in {
    SQL.mostViewedCopyOfEachVideo(Seq(
      VideoInfo("CRjZsa4VtkI","18.16.03","ФУРРИ ИЗ СЕВЕРНОЙ КОРЕИ | Разбор мультсериала Бурундук и Ёжик | Сыендук","Сыендук",24,"2018-03-15T16:25:19.000Z","\"squirrel and hedgehog\"",909811,95963,1154,9142,"https://i.ytimg.com/vi/CRjZsa4VtkI/default.jpg",false,false,false,"Скачай Викинги, получи 200 золота!\nAndroid - http://bit.ly/2HyVm6f \niOS - http://bit.ly/2Dqx3EY \nКОНКУРС MacBook Pro - https://vk.com/club163422856\n----\nСЫЕНДУК ВКОНТАКТЕ: http://vk.com/sienduk \nВТОРОЙ КАНАЛ: https://www.youtube.com/user/siendukLIVE\nТВИТТЕР: https://twitter.com/sienduk \nINSTAGRAM: http://instagram.com/siend_k/\n\nАрт с лисой на превьюшке: https://iggler.deviantart.com/art/Lt-Fox-Vixen-432487670\n\nФОНОВАЯ МУЗЫКА ИЗ ВИДЕО: [скоро!]"),
      VideoInfo("CRjZsa4VtkI","18.17.03","ФУРРИ ИЗ СЕВЕРНОЙ КОРЕИ | Разбор мультсериала Бурундук и Ёжик | Сыендук","Сыендук",24,"2018-03-15T16:25:19.000Z","\"squirrel and hedgehog\"",1496149,131212,1851,12042,"https://i.ytimg.com/vi/CRjZsa4VtkI/default.jpg",false,false,false,"Скачай Викинги, получи 200 золота!\nAndroid - http://bit.ly/2HyVm6f \niOS - http://bit.ly/2Dqx3EY \nКОНКУРС MacBook Pro - https://vk.com/club163422856\n----\nСЫЕНДУК ВКОНТАКТЕ: http://vk.com/sienduk \nВТОРОЙ КАНАЛ: https://www.youtube.com/user/siendukLIVE\nТВИТТЕР: https://twitter.com/sienduk \nINSTAGRAM: http://instagram.com/siend_k/\n\nАрт с лисой на превьюшке: https://iggler.deviantart.com/art/Lt-Fox-Vixen-432487670\n\nФОНОВАЯ МУЗЫКА ИЗ ВИДЕО: https://vk.com/wall-26713492_467369"),
      VideoInfo("CRjZsa4VtkI","18.18.03","ФУРРИ ИЗ СЕВЕРНОЙ КОРЕИ | Разбор мультсериала Бурундук и Ёжик | Сыендук","Сыендук",24,"2018-03-15T16:25:19.000Z","\"squirrel and hedgehog\"",1660673,139183,2005,12975,"https://i.ytimg.com/vi/CRjZsa4VtkI/default.jpg",false,false,false,"Скачай Викинги, получи 200 золота!\nAndroid - http://bit.ly/2HyVm6f \niOS - http://bit.ly/2Dqx3EY \nКОНКУРС MacBook Pro - https://vk.com/club163422856\n----\nСЫЕНДУК ВКОНТАКТЕ: http://vk.com/sienduk \nВТОРОЙ КАНАЛ: https://www.youtube.com/user/siendukLIVE\nТВИТТЕР: https://twitter.com/sienduk \nINSTAGRAM: http://instagram.com/siend_k/\n\nАрт с лисой на превьюшке: https://iggler.deviantart.com/art/Lt-Fox-Vixen-432487670\n\nФОНОВАЯ МУЗЫКА ИЗ ВИДЕО: https://vk.com/wall-26713492_467369")
    )) should contain theSameElementsAs Seq(VideoInfo("CRjZsa4VtkI","18.18.03","ФУРРИ ИЗ СЕВЕРНОЙ КОРЕИ | Разбор мультсериала Бурундук и Ёжик | Сыендук","Сыендук",24,"2018-03-15T16:25:19.000Z","\"squirrel and hedgehog\"",1660673,139183,2005,12975,"https://i.ytimg.com/vi/CRjZsa4VtkI/default.jpg",false,false,false,"Скачай Викинги, получи 200 золота!\nAndroid - http://bit.ly/2HyVm6f \niOS - http://bit.ly/2Dqx3EY \nКОНКУРС MacBook Pro - https://vk.com/club163422856\n----\nСЫЕНДУК ВКОНТАКТЕ: http://vk.com/sienduk \nВТОРОЙ КАНАЛ: https://www.youtube.com/user/siendukLIVE\nТВИТТЕР: https://twitter.com/sienduk \nINSTAGRAM: http://instagram.com/siend_k/\n\nАрт с лисой на превьюшке: https://iggler.deviantart.com/art/Lt-Fox-Vixen-432487670\n\nФОНОВАЯ МУЗЫКА ИЗ ВИДЕО: https://vk.com/wall-26713492_467369"))
  }

  override protected def beforeAll(): Unit = {
    Class.forName("org.h2.Driver")
    ConnectionPool.singleton("jdbc:h2:mem:example;DB_CLOSE_DELAY=-1", "", "")

    DB.autoCommit(session => {
      SQL.initDb()
    })
  }
}
