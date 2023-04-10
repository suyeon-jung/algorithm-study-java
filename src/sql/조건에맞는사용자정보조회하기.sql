-- 코드를 입력하세요
SELECT USER_ID, NICKNAME, CONCAT(CITY, ' ', STREET_ADDRESS1, IF(STREET_ADDRESS2 IS NULL, NULL, CONCAT(' ', STREET_ADDRESS2))) "전체주소",
       CONCAT(SUBSTR(TLNO, 1, 3), '-', SUBSTR(TLNO, 4, 4), '-', SUBSTR(TLNO, 8, 4)) "전화번호"
FROM USED_GOODS_BOARD a
         JOIN USED_GOODS_USER b on a.WRITER_ID = b.USER_ID
GROUP BY USER_ID
HAVING COUNT(BOARD_ID) >= 3
ORDER BY USER_ID DESC;

