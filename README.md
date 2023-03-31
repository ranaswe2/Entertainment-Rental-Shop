# Entertainment Rental Shop

The system being developed, will be used for a rental store that allows
employees to loan out items. These loaned items will then be returned to the
store after use by the public, and they can be tracked to see overdue fees and
who currently has the item.
This system will have a fully functional GUI that’ll allow for search capabilities
(to see item availability and details) and a login page that the user will have to
pass before entering the system, this will also be used by employees of the store
(not the public) that can register other users, but only the admin can approve
this, the admin can also reset passwords, unlock accounts and delete users.
Furthermore, the system will allow for different items (books, DVDs, CDs, and
Audiobooks) to be catalogued within the database. In addition to these, each
item will have different options (for example, CDs will have their genre).
Other features of the system will include a monthly report that'll show the user
the most popular item, the number of times each item has been loaned and
returned, and the total of items that have been landed and returned.</br>

![ERS](/src/start/logo.png "ERS") </br>

### The system must include the following features:
</br>
● Loan and return items, Tracking details of who has it.</br>
● Users can reserve items before loaning.</br>
● Fine for overdue items with details</br>
● Charge for losing or missing an item.</br>
● Ability to select multiple types of items to be loaned at once.</br>
● More than one option for each of the items </br>
● Shows item availability when loaning and the quantity.</br>
● Option to print details about a single item onto a file that includes; item
details, number of copies on loan and available, and the earliest return
date</br>
● Search facility and inventory checking</br>
● Login system with different users (standard and admin), with the ability
to register new users with admin approval.</br>
● Option to log off once user us finished</br>
● Cataloguing ability to register new items, update items and delete items,
within the shops database</br>
● Monthly report page that will check most popular item, number of times
each item has been loaned and returned, and total of items that have been
landed and returned</br>

![ERS](/images/requirements.png "ERS") </br>

### Instructions
I. Create a database named entertainment_rental_shop with user name root and empty password </br>
    a. If you set password, then go to database package and then change the password field of MySQL.java class </br>

II. Import the entertainment_rental_shop.sql file  </br>

III. Import the project zip file in netbeans IDE by following path: </br>
      File > Import Project > From ZIP > select the project  ZIP file from its location </br>

IV. Run the project.  </br>
     a. If it starts with showing a progressbar, then it’s okay. Because, our project Main Class is StartWindow.java which is in the start package. </br>
     b. Else, go to src > start > StartWindow.java and run the file. </br>

## Features with User Interface

![ERS](/images/0start.png "ERS") </br>
# .
![ERS](/images/1start.png "ERS") </br>

## User Panel


#### Sign Up
</br>
* Select a valid Image with extension JPG, JPEG or PNG formate</br>
* Provide a username with the combination of alphabets and numbers</br>

![ERS](/images/user_signup1.png "ERS") </br>

After clicking the Sign Up button, system check your information. If all are correct, then you will see a pop-up message </br>

![ERS](/images/user_signup2.png "ERS") </br>

#### Log In

Try to log in before approving the user account</br>
![ERS](/images/user_login1.png "ERS") </br>

Try to log in with an incorrect password </br>


![ERS](/images/user_login2.png "ERS") </br>
 </br>
![ERS](/images/user_login3.png "ERS") </br>
 </br>              
![ERS](/images/user_login4.png "ERS") </br>
After trying 3 times with a wrong password, account is blocked  </br>
![ERS](/images/user_login5.png "ERS") </br>

#### Home

![ERS](/images/user_home.png "ERS") </br>

#### Search Product

* Select a category and enter search text</br>
* Click on the product image to view product details</br>
* Click 'Take a loan' button for taking loan</br>
* Click 'Add to Cart' to reserve the product for future loan</br>
![ERS](/images/user_search.png "ERS") </br>

#### Loan Request

![ERS](/images/user_loanreq.png "ERS") </br>

#### Reserved Product

![ERS](/images/user_cart.png "ERS") </br>

#### Loan History

![ERS](/images/user_loan.png "ERS") </br>

#### Logout

![ERS](/images/user_logout.png "ERS") </br>


## Admin Panel

#### Login

![ERS](/images/admin_login.png "ERS") </br>

#### Home

![ERS](/images/admin_home.png "ERS") </br>
Click on the product image for detail information </br>
![ERS](/images/admin_home2.png "ERS") </br>
 Enter User ID for checking borrower information </br>
![ERS](/images/admin_home3.png "ERS") </br>
Approve or remove after checking the information
![ERS](/images/admin_home4.png "ERS") </br>



#### User Request

![ERS](/images/admin_userreq1.png "ERS") </br>
 </br>
![ERS](/images/admin_userreq2.png "ERS") </br>



#### New Item

![ERS](/images/admin_item1.png "ERS") </br>
A pop-up message will appear on the screen after clocking the Add Item button </br>
![ERS](/images/admin_item1.png "ERS") </br>


#### New Product

Existing Product List
![ERS](/images/admin_product1.png "ERS") </br>
Add a new Product
![ERS](/images/admin_product2.png "ERS") </br>
A pop-up message will appear on the screen after Successfully added the product
![ERS](/images/admin_product3.png "ERS") </br>
Click the New Product button for check the update list
![ERS](/images/admin_product4.png "ERS") </br>


#### Manage User

* Update user and admin password </br>
* Delete unusual user account </br>
![ERS](/images/admin_manageuser.png "ERS") </br>


#### Manage Item

Update Item Pricing </br>
![ERS](/images/admin_manageitem2.png "ERS") </br>

Remove Item from the Item List </br>
![ERS](/images/admin_manageitem3.png "ERS") </br>
 </br>
![ERS](/images/admin_manageitem4.png "ERS") </br>


#### Loan Summary

![ERS](/images/admin_loan.png "ERS") </br>
Receive Product from borrower </br>
* Enter Loan Id </br>
* Select payment Type </br>
* Click check amount button </br>
* Click the receive button </br>
![ERS](/images/admin_loan2.png "ERS") </br>
* A pop-up message will appear on the screen if the system receive this payment successfully </br>
![ERS](/images/admin_loan2.png "ERS") </br>

#### Monthly Report
* Click 'Monthly Report' button to download report for current month </br>
* Select a folder where you want to keep the report</br>
* Click save button</br>
![ERS](/images/admin_report3.png "ERS") </br>


#### Item Report

* Click 'Item Report' button to download report for a specific item </br>
* Select an item </br>
* Click download button  </br>
* Select a folder where you want to keep the report </br>
* Click save button </br>
![ERS](/images/admin_report1.png "ERS") </br>
</br></br>
![ERS](/images/admin_report2.png "ERS") </br>


#### Logout

![ERS](/images/admin_logout.png "ERS") </br>

</br></br>

*Thank You for Visiting this Repository*










