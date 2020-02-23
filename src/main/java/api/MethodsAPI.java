package api;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.List;
import util.UserJsonHandler;

@Path("methods")
public class MethodsAPI {

    private final static UserJsonHandler userJsonHandler = new UserJsonHandler();

    @POST
    @Path("userswithreversednames")
    public static Response usersWithReversedNames() {
        return Response.ok(userJsonHandler.usersWithReversedNames()).build();
    }

    @POST
    @Path("reversestring")
    public static Response reverseString(@FormParam("string") String string) {
        return Response.ok(userJsonHandler.reverseString(string)).build();
    }

    @POST
    @Path("ispalindrome")
    public static Response isPalindrome(@FormParam("string") String string) {
        return Response.ok(userJsonHandler.isPalindrome(string)).build();
    }

    @POST
    @Path("padnumberwithzeroes")
    public static Response padNumberWithZeroes(@FormParam("number") int number) {
        return Response.ok(userJsonHandler.padNumberWithZeroes(number)).build();
    }

    @POST
    @Path("findnthlargestnumber")
    public static Response findNthLargestNumber(@FormParam("numbers") List<Integer> numbers,
                                                @FormParam("nthlargestnumber") int nthLargestNumber) {
        return Response.ok(userJsonHandler.findNthLargestNumber(numbers, nthLargestNumber)).build();
    }

}
