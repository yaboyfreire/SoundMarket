####ver todos os álbuns na coleção de um user
SELECT *
FROM album
WHERE album_user_id = 1;

####ver todos os álbuns na coleção de um user ordenados por ordem alfabética
SELECT *
FROM album
WHERE album_user_id = 1
ORDER BY album_title ASC;

####mostrar todos os álbuns à venda que não são do utilizador
select *
from album, selling
WHERE album.album_id = selling_album_id
AND selling_user_id != 1;

####mostrar todos os álbuns à venda que são do utilizador
select *
from album, selling
WHERE album.album_id = selling_album_id
AND selling_user_id = 1;

####Mostrar todas as unidades de um álbum à venda pelo nome
SELECT album_title,selling_description, selling_price,selling_created_at
FROM album, selling
where album_id = selling_album_id
and album_title = 'Lemonade';

####ver chat entre dois utilizadores
SELECT msg_sender_id, msg_text
FROM chat, message
where chat_id = msg_chat_id
and (chat_seller_id = 1 AND chat_buyer_id = 2)
OR (chat_seller_id = 2 AND chat_buyer_id = 1);

####mostra todas os os álbuns que foram vendidos e pagos
SELECT buy_id, buy_price_paid, buy_created_at, state_type
FROM buy, state
Where buy_status_id = state_id
and state_type = 'Paid';


####ver data de nascimento dos utilizadores 
SELECT user_name , user_bday
FROM utilizador;

####conta a quantidade de users do sexo masculino e do sexo femenino que estão registados na aplicação
SELECT user_gender, COUNT(*) AS user_count
FROM utilizador
GROUP BY user_gender;

####mostra a password hashed do user
SELECT user_passwordhash
FROM utilizador
WHERE user_email = 'joaosilva@gmail.com';

