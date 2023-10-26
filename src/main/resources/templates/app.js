function validateForm() {
  let name = document.forms["paymentform"]["name"].value;
  if (name == "") {
    alert("Name must be filled out");
    document.forms["paymentform"]["name"].focus();
    return false;
  }
  let cardnumber = document.forms["paymentform"]["cardnumber"].value;
    if (cardnumber == "") {
      alert("Card Number must be filled out");
      document.forms["paymentform"]["cardnumber"].focus();
      return false;
    }
    let cvv = document.forms["paymentform"]["cvv"].value;
      if (cvv == "") {
        alert("CVV must be filled out");
        document.forms["paymentform"]["cvv"].focus();
        return false;
      }
      let expirydate = document.forms["paymentform"]["expirydate"].value;
        if (expirydate == "") {
          alert("Expiry Date must be filled out");
          document.forms["paymentform"]["expirydate"].focus();
          return false;
        }
}