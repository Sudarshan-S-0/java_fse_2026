SET SERVEROUTPUT ON;

BEGIN

FOR rec IN
(
SELECT c.CustomerName,
       l.LoanID,
       l.DueDate
FROM Customers c
JOIN Loans l
ON c.CustomerID=l.CustomerID
WHERE l.DueDate BETWEEN SYSDATE AND SYSDATE+30
)

LOOP

DBMS_OUTPUT.PUT_LINE(
'Reminder : '
||rec.CustomerName||
' Loan ID : '||
rec.LoanID||
' Due Date : '||
TO_CHAR(rec.DueDate,'DD-MON-YYYY')
);

END LOOP;

END;
/