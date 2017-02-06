/**
 * 20170206 PN CAM-137 INIT file to implement error handling methods
 */

/*
 * 
 */
function displayErrorMessage(x, status, error) {
	
}

/*
 * This method is to catch 4xx Client Errors. The 4xx class of status codes is
 * intended for situations in which the client seems to have erred. Except when
 * responding to a HEAD request, the server should include an entity containing
 * an explanation of the error situation, and whether it is a temporary or
 * permanent condition. These status codes are applicable to any request method.
 * User agents should display any included entity to the user. @param status -
 * error status code.
 */
function getServerErrorMessage(status){
	switch(status) {
    case 500:
        return 'Internal Server Error.';
        break;
    case 501:
    	return 'Not implemented method call.';
        break;
    case 502:
    	return 'Bad Gateway.';
        break;
    case 503:
    	return 'The server is currently unavailable';
        break;
    case 504:
    	return 'Gateway time-out';
        break;
    case 505:
    	return 'HTTP Version Not Supported';
        break;
    case 506:
    	return 'The server request results in a circular reference.';
        break;
    case 507:
    	return 'The server is unable to store the representation needed to complete the request.';
        break;
    case 508:
    	return 'The server detected an infinite loop while processing the request.';
        break;
    case 510:
    	return 'Not extended server request.';
        break;
    case 511:
    	return 'The client needs to authenticate to gain network access.';
        break;
    default:
    	return 'Unexpected error encountered.';
	}
}
/*
 * This method is to catch 5xx Server Error The server failed to fulfill an
 * apparently valid request. Response status codes beginning with the digit "5"
 * indicate cases in which the server is aware that it has encountered an error
 * or is otherwise incapable of performing the request. Except when responding
 * to a HEAD request, the server should include an entity containing an
 * explanation of the error situation, and indicate whether it is a temporary or
 * permanent condition. Likewise, user agents should display any included entity
 * to the user. These response codes are applicable to any request method.
 */
function getClientErrorMessage(status){
	case 400: 
		return 'The server cannot process the request due to an apparent client error.';
		break;
	case 401: 
		return 'Unauthorized request.';
		break;
	case 402: 
		return 'Payment Required.';
		break;
	case 403: 
		return 'Access Forbidden.';
		break;
	case 404: 
		return 'Page Not Found.';
		break;
	case 405: 
		return 'Method Not Allowed.';
		break;
	case 406: 
		return 'Not acceptable request.';
		break;
	case 407: 
		return 'Proxy Authentication Required.';
		break;
	case 408: 
		return 'Request Time-out.';
		break;
	case 409: 
		return 'Request could not be processed because of conflict in the request.';
		break;
	case 410: 
		return 'Resource requested is no longer available and will not be available again.';
		break;
	case 411: 
		return 'The request did not specify the length of its content, which is required by the requested resource.';
		break;
	case 412: 
		return 'The server does not meet one of the preconditions that the requester put on the request.';
		break;
	case 413: 
		return 'The request is larger than the server is willing or able to process.';
		break;
	case 414: 
		return 'The URI provided was too long for the server to process.';
		break;
	case 415: 
		return 'Unsupported Media Type.';
		break;
	case 416: 
		return 'Range Not Satisfiable.';
		break;
	case 417: 
		return 'Expectation Failed.';
		break;
	case 418: 
		return 'I\'m a teapot. Fake error message.';
		break;
	case 421: 
		return 'Misdirected Request.';
		break;
	case 422: 
		return 'Unprocessable Entity.';
		break;
	case 423: 
		return 'The resource that is being accessed is locked.';
		break;
	case 424: 
		return 'Failed Dependency.';
		break;
	case 426: 
		return 'Upgrade Required';
		break;
	case 428: 
		return 'Precondition Required.';
		break;
	case 429: 
		return 'Too Many Requests.';
		break;
	case 431: 
		return 'Request Header Fields Too Large.';
		break;
	case 451: 
		return 'Unavailable For Legal Reasons.';
		break;
    default:
    	return 'Unexpected error encountered.';
}