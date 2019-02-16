package com.binarydistrict.scala.exercise8

import akka.http.scaladsl.server.Route

object Problem1 {
  /*
    Реализовать API для своего Twitter-а
    POST /user                     json{username, session_id}                    - регистрация пользователя. session_id уникальный, по нему мы можем найти пользователя. Возвращает просто 200 или 409, если пользователи или session_id уже есть
    POST /post                     json{message, session_id}                     - отправить сообщение, возвращает message_id сообщения. В сообщении не должно быть больше 140 символов, иначе надо бросить код 400.
    DELETE /post                   json{session_id, message_id}                  - удалить сообщение. 404, если нет
    GET /post/{username}           [json{message, username}]                     - получить сообщения пользователя. 404, если его нет
    GET /post/{username}/page/{n}  [json{message, username}]                     - получить сообщения пользователя по страницам. 404, если его нет или страницы
    GET /stats                     [words_count, messages_count, users_count]    - получить статистику.
   */
  val route: Route = ???
}
